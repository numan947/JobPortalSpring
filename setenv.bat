@echo off
for /f "tokens=1,2 delims==" %%a in ('type .env') do (
    setx %%a %%b
)
