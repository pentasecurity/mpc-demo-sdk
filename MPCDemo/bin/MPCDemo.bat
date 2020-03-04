@echo off

set CRR=%~dp0
set LIBRARY_PATH=%CRR%\lib\native
set JARS=%CRR%\MPCDemo-1.0.jar

for %%j in (%CRR%\lib\*.jar) do call :add_jar %%j

java -cp %JARS% -Djava.library.path=%LIBRARY_PATH% com.pentampc.demo.MPCDemo %*
exit /b

:add_jar
set JARS=%JARS%;%1
exit /b