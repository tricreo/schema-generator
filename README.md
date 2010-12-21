Schema Generator Application
==================

How to build
-----------

Required Environment : Java 1.5+, Maven2

$ mvn clean package

$ mvn dependency:copy-dependencies -DoutputDirectory=bin/lib

$ cp target/schema-generator-0.0.1-SNAPSHOT.jar bin/lib

How to use
----------

$ cd bin

$ sch-gen config.properties

License
-------
Copyright (C) 2010 [TRICREO, Inc.](http://tricreo.jp/).

Distributed under the Apache License v2.0.  See the file copyright/LICENSE.txt.
