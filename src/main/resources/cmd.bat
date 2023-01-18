echo OFF
setlocal

:again
set /p inputLine=""
echo.%inputLine%
if not (%inputLine%)==() goto again

endlocal
:End
