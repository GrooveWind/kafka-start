@echo off
title groove-Update project version and deploy
rem color 02

set snapshotUrl=http://abc/nexus/content/xxx/
set snapshotId=snapshots

set /p curdir=������������Ŀ·��:
%curdir:~0,2%
cd %curdir%
echo ���뵱ǰ·��: %curdir%

set /p currVersion=���������İ汾��:


echo ======================= ��ʼ���°汾�� =======================
echo;
rem call mvn versions:set -DnewVersion=%currVersion%
echo;
echo ======================= ���°汾����� =======================

choice /C YN /M "����ʮ���Ӽ��ʱ����gateway��market����Ŀ������������ִ�������밴 Y,��ִ�������밴 N"  /D Y /T 600
if errorlevel 2 goto end

echo ======================= ��ʼ���͸�pom��maven�ֿ� =======================
echo;
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove -DpomFile=pom.xml -Dfile=%curdir%\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
echo;
echo ======================= ��pom���ͽ��� =======================
echo;


echo ======================= ��ʼ����common��Ŀ�ļ���maven�ֿ� =======================
echo;
cd groove_commons
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove_commons -DpomFile=pom.xml -Dfile=%curdir%\groove_commons\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
cd groove_global_common
call mvn clean deploy -Dmaven.test.skip=true
cd ../groove_dao_common
call mvn clean deploy -Dmaven.test.skip=true
cd ../groove_server_common
call mvn clean deploy -Dmaven.test.skip=true
cd %curdir%
echo;
echo ======================= ����common��Ŀ�ļ����ͽ��� =======================
echo;


echo ======================= ��ʼ����front��Ŀ�ļ���maven�ֿ� =======================
echo;
cd groove_front_server
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove_front_server -DpomFile=pom.xml -Dfile=%curdir%\groove_front_server\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
cd groove_front_service
call mvn clean deploy -Dmaven.test.skip=true
cd %curdir%
echo;
echo ======================= ����front��Ŀ�ļ����ͽ��� =======================
echo;


echo ======================= ��ʼ����deal��Ŀ�ļ���maven�ֿ� =======================
echo;
cd groove_deal_server
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove_deal_server -DpomFile=pom.xml -Dfile=%curdir%\groove_deal_server\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
cd groove_deal_service
call mvn clean deploy -Dmaven.test.skip=true
cd %curdir%
echo;
echo ======================= ����deal��Ŀ�ļ����ͽ��� =======================
echo;


echo ======================= ��ʼ����account��Ŀ�ļ���maven�ֿ� =======================
echo;
cd groove_account_server
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove_account_server -DpomFile=pom.xml -Dfile=%curdir%\groove_account_server\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
cd groove_account_service
call mvn clean deploy -Dmaven.test.skip=true
cd %curdir%
echo;
echo ======================= ����account��Ŀ�ļ����ͽ��� =======================
echo;


:end
echo good bye
pause