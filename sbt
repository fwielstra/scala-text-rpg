java -Dfile.encoding=UTF8 -Xmx512M -Xss1M -XX:MaxPermSize=256M -XX:+CMSClassUnloadingEnabled -jar `dirname $0`/sbt-launch.jar "$@"
