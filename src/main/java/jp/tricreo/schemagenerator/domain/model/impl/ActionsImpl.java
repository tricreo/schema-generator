package jp.tricreo.schemagenerator.domain.model.impl;

import java.sql.Connection;
import java.util.List;

import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.ActionContext;
import jp.tricreo.schemagenerator.domain.model.Actions;
import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.domain.model.DataSourceConnectService;
import jp.tricreo.schemagenerator.exception.CloneNotSupportedRuntimeException;
import jp.tricreo.schemagenerator.infrastructure.utils.CloneUtil;
import jp.tricreo.schemagenerator.infrastructure.utils.CloseableUtil;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.LoggerFactory;


/**
 * {@link Actions}の実装クラス。
 * 
 * <p>不変オブジェクト版のエンティティ。
 * {@code dataSource}と{@code actions}は可変オブジェクトなので
 * clone戦略を採用している。</p>
 * 
 * @author junichi
 */
public class ActionsImpl implements Actions {
	
	private final String identity;
	
	private final DataSource dataSource;
	
	private final List<Action<?>> actions;
	
	private final DataSourceConnectService dataSourceConnectService;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param identity 識別子
	 * @param dataSource {@link DataSource}
	 * @param dataSourceConnectService {@link DataSourceConnectService}
	 * @param actions {@link Action}のリスト
	 */
	public ActionsImpl(String identity, DataSource dataSource,
			DataSourceConnectService dataSourceConnectService,
			List<Action<?>> actions) {
		Validate.notNull(identity);
		Validate.notNull(dataSource);
		Validate.notNull(dataSourceConnectService);
		Validate.notNull(actions);
		this.identity = identity;
		// 可変オブジェクトの場合はクローンを作って副作用を防ぐ
		this.dataSource = dataSource.clone();
		this.dataSourceConnectService = dataSourceConnectService;
		// 不変オブジェクトのコレクションは可変オブジェクトなので、クローンを作って副作用を防ぐ
		this.actions = CloneUtil.cloneValueArrayList(actions);
	}
	
	@Override
	public final ActionsImpl clone() {
		try {
			// シャローコピー(浅い複製)だが、getterアクセス時にcloneされる。
			// 全く同じインスタンスの属性を持ちながら、可変アクセスを制限。
			ActionsImpl clone = (ActionsImpl) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new CloneNotSupportedRuntimeException(e);
		}
	}
	
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ActionsImpl other = (ActionsImpl) obj;
		return sameIdentityAs(other);
	}
	
	@Override
	public void execute() {
		Connection connection = null;
		try {
			connection = dataSourceConnectService.connect(dataSource);
			for (Action<?> action : actions) {
				ActionContext actionContext =
						new ActionContext(LoggerFactory.getLogger(action
							.getClass()), connection);
				action.execute(actionContext);
			}
		} finally {
			CloseableUtil.close(connection);
		}
	}
	
	@Override
	public List<Action<?>> getActions() {
		// Listは可変オブジェクトなのでクローンする。
		return CloneUtil.cloneValueArrayList(actions);
	}
	
	@Override
	public final DataSource getDataSource() {
		// エンティティはクローンを作って副作用を防ぐ
		return dataSource.clone();
	}
	
	@Override
	public String getIdentity() {
		return identity;
	}
	
	@Override
	public final int hashCode() {
		return new HashCodeBuilder().append(getIdentity()).toHashCode();
	}
	
	@Override
	public final boolean sameIdentityAs(Actions other) {
		return new EqualsBuilder().append(getIdentity(), other.getIdentity())
			.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
