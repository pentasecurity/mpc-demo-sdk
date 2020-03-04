#!/bin/sh
MPCDemo=MPCDemo-1.0.jar

REPOS=`ls -d lib/*.jar`
for repo in $REPOS
do
	MPCDemo=$MPCDemo:$repo
done

MPCLIBRARY=lib/native

java -cp "${MPCDemo}" -Djava.library.path=${MPCLIBRARY} com.pentampc.demo.MPCDemo $@
