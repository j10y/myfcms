echo %path%
cd %~dp0
%~d0
native2ascii applicationResources_zh_CN.properties ..\WebRoot\WEB-INF\classes\applicationResources_zh_CN.properties
pause
