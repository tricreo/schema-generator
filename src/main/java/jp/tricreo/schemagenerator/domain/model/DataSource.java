package jp.tricreo.schemagenerator.domain.model;

import jp.tricreo.schemagenerator.exception.CloneNotSupportedRuntimeException;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * データソースを表すクラス。
 * 
 * <p>
 * データソースとは、JDBCで接続できるデータベースの接続情報を意味する。
 * 可変なエンティティ。
 * </p>
 * 
 * @author junichi
 */
public class DataSource implements Entity<DataSource, String> {
	
	// IDは必ずfinal
	private final String identity;
	
	private String driverClassName;
	
	private String url;
	
	private String userName;
	
	private String password;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param identity 識別子
	 */
	public DataSource(String identity) {
		this(identity, null, null, null, null);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param identity 識別子
	 * @param driverClassName ドライバークラス名 
	 * @param url URL
	 * @param userName ユーザ名
	 * @param password パスワード
	 */
	public DataSource(String identity, String driverClassName, String url,
			String userName, String password) {
		Validate.notNull(identity);
		this.identity = identity;
		this.driverClassName = driverClassName;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	public final DataSource clone() {
		try {
			DataSource clone = (DataSource) super.clone();
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
		DataSource other = (DataSource) obj;
		return sameIdentityAs(other);
	}
	
	/**
	 * ドライバークラス名を取得する。
	 * 
	 * @return ドライバークラス名
	 */
	public String getDriverClassName() {
		return driverClassName;
	}
	
	/**
	 * 識別子を取得する。
	 * 
	 * @return 識別子
	 */
	@Override
	public String getIdentity() {
		return identity;
	}
	
	/**
	 * パスワードを取得する。
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * URLを取得する。
	 * 
	 * @return URL
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * ユーザ名を取得する。
	 * 
	 * @return ユーザ名
	 */
	public String getUserName() {
		return userName;
	}
	
	@Override
	public final int hashCode() {
		// commons-langの機能を使って、ハッシュコードを計算する
		return new HashCodeBuilder().append(getIdentity()).toHashCode();
	}
	
	@Override
	public final boolean sameIdentityAs(DataSource other) {
		// commons-langの機能を使って等価を判定する
		return new EqualsBuilder().append(getIdentity(), other.getIdentity())
			.isEquals();
	}
	
	/**
	 * ドライバークラス名を設定する。
	 * 
	 * @param driverClassName ドライバークラス名
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	/**
	 * パスワードを設定する。
	 * 
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * URLを設定する。
	 * 
	 * @param url URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * ユーザ名を設定する。
	 * 
	 * @param userName ユーザ名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
