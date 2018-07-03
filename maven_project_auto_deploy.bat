@echo off
title groove-Update project version and deploy
rem color 02

set snapshotUrl=http://abc/nexus/content/xxx/
set snapshotId=snapshots

set /p curdir=请输入您的项目路径:
%curdir:~0,2%
cd %curdir%
echo 进入当前路径: %curdir%

set /p currVersion=请输入您的版本号:


echo ======================= 开始更新版本号 =======================
echo;
rem call mvn versions:set -DnewVersion=%currVersion%
echo;
echo ======================= 更新版本号完毕 =======================

choice /C YN /M "您有十分钟检查时间检查gateway、market等项目的依赖，继续执行推送请按 Y,不执行推送请按 N"  /D Y /T 600
if errorlevel 2 goto end

echo ======================= 开始推送父pom至maven仓库 =======================
echo;
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove -DpomFile=pom.xml -Dfile=%curdir%\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
echo;
echo ======================= 父pom推送结束 =======================
echo;


echo ======================= 开始推送common项目文件至maven仓库 =======================
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
echo ======================= 推送common项目文件推送结束 =======================
echo;


echo ======================= 开始推送front项目文件至maven仓库 =======================
echo;
cd groove_front_server
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove_front_server -DpomFile=pom.xml -Dfile=%curdir%\groove_front_server\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
cd groove_front_service
call mvn clean deploy -Dmaven.test.skip=true
cd %curdir%
echo;
echo ======================= 推送front项目文件推送结束 =======================
echo;


echo ======================= 开始推送deal项目文件至maven仓库 =======================
echo;
cd groove_deal_server
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove_deal_server -DpomFile=pom.xml -Dfile=%curdir%\groove_deal_server\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
cd groove_deal_service
call mvn clean deploy -Dmaven.test.skip=true
cd %curdir%
echo;
echo ======================= 推送deal项目文件推送结束 =======================
echo;


echo ======================= 开始推送account项目文件至maven仓库 =======================
echo;
cd groove_account_server
call mvn deploy:deploy-file -DgroupId=me.groove -DartifactId=groove_account_server -DpomFile=pom.xml -Dfile=%curdir%\groove_account_server\pom.xml -Durl=%snapshotUrl% -DrepositoryId=%snapshotId%
cd groove_account_service
call mvn clean deploy -Dmaven.test.skip=true
cd %curdir%
echo;
echo ======================= 推送account项目文件推送结束 =======================
echo;


:end
echo good bye
pause