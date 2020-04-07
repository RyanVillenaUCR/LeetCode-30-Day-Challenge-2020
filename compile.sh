test -d temp/ || mkdir temp/
echo "Compiling..." \
    && javac -d temp/ src/*.java \
    && echo "Compiled!"
