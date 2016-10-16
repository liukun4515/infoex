
echo "start extract information"
if [ $# -lt 1 ] ; then
	echo "please add input path"
	exit 0
fi
mvn exec:java -Dexec.mainClass="com.thu.database.infoex.example.Example1" -Dexec.args="$1"