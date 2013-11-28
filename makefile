JAVAC=javac
sources = Viewer.java
classes = $(sources:.java=.class)

all: $(classes)

clean :
		rm -f *.class

%.class : %.java
		$(JAVAC) $<
