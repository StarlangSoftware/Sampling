# Sampling
For Developers
============
You can also see either [Python](https://github.com/olcaytaner/Sampling-Py) 
or [C++](https://github.com/olcaytaner/Sampling-CPP) repository.
## Requirements

* [Java Development Kit 8 or higher](#java), Open JDK or Oracle JDK
* [Maven](#maven)
* [Git](#git)

### Java 

To check if you have a compatible version of Java installed, use the following command:

    java -version
    
If you don't have a compatible version, you can download either [Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or [OpenJDK](https://openjdk.java.net/install/)    

### Maven
To check if you have Maven installed, use the following command:

    mvn --version
    
To install Maven, you can follow the instructions [here](https://maven.apache.org/install.html).      

### Git

Install the [latest version of Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

## Download Code

In order to work on code, create a fork from GitHub page. 
Use Git for cloning the code to your local or below line for Ubuntu:

	git clone <your-fork-git-link>

A directory called Sampling will be created. Or you can use below link for exploring the code:

	git clone https://github.com/olcaytaner/Sampling.git

## Open project with IntelliJ IDEA

Steps for opening the cloned project:

* Start IDE
* Select **File | Open** from main menu
* Choose `Sampling/pom.xml` file
* Select open as project option
* Couple of seconds, dependencies with Maven will be downloaded. 


## Compile

**From IDE**

After being done with the downloading and Maven indexing, select **Build Project** option from **Build** menu. After compilation process, user can run Sampling.

**From Console**

Go to `Sampling` directory and compile with 

     mvn compile 

## Generating jar files

**From IDE**

Use `package` of 'Lifecycle' from maven window on the right and from `Sampling` root module.

**From Console**

Use below line to generate jar file:

     mvn install

## Maven Usage

	<dependency>
  	<groupId>NlpToolkit</groupId>
  	<artifactId>Sampling</artifactId>
  	<version>1.0.0</version>
	</dependency>


------------------------------------------------

Detailed Description
============
+ [CrossValidation](#crossvalidation)
+ [Bootstrap](#bootstrap)
+ [KFoldCrossValidation](#kfoldcrossvalidation)
+ [StratifiedKFoldCrossValidation](#stratifiedkfoldcrossvalidation)

## CrossValidation

k. eğitim kümesini elde etmek için

	ArrayList<T> getTrainFold(int k)

k. test kümesini elde etmek için

	ArrayList<T> getTestFold(int k)

## Bootstrap

Bootstrap için BootStrap sınıfı

	Bootstrap(ArrayList<T> instanceList, int seed)

Örneğin elimizdeki veriler a adlı ArrayList'te olsun. Bu veriler üstünden bir bootstrap 
örneklemi tanımlamak için (5 burada rasgelelik getiren seed'i göstermektedir. 5 
değiştirilerek farklı samplelar elde edilebilir)

	bootstrap = Bootstrap(a, 5);

ardından üretilen sample'ı çekmek için ise

	sample = bootstrap.getSample();

yazılır.

## KFoldCrossValidation

K kat çapraz geçerleme için KFoldCrossValidation sınıfı

	KFoldCrossValidation(List<T> instanceList, int K, int seed)

Örneğin elimizdeki veriler a adlı ArrayList'te olsun. Bu veriler üstünden 10 kat çapraz 
geçerleme yapmak için (2 burada rasgelelik getiren seed'i göstermektedir. 2 
değiştirilerek farklı samplelar elde edilebilir)

	kfold = KFoldCrossValidation(a, 10, 2);

ardından yukarıda belirtilen getTrainFold ve getTestFold metodları ile sırasıyla i. eğitim
ve test kümeleri elde edilebilir. 

## StratifiedKFoldCrossValidation

Stratified K kat çapraz geçerleme için StratifiedKFoldCrossValidation sınıfı

	StratifiedKFoldCrossValidation(ArrayList<T>[] instanceLists, int K, int seed)

Örneğin elimizdeki veriler a adlı ArrayList of listte olsun. Stratified bir çapraz 
geçerlemede sınıflara ait veriler o sınıfın oranında temsil edildikleri için her bir 
sınıfa ait verilerin ayrı ayrı ArrayList'te olmaları gerekmektedir. Bu veriler üstünden 
30 kat çapraz geçerleme yapmak için (4 burada rasgelelik getiren seed'i göstermektedir. 4 
değiştirilerek farklı samplelar elde edilebilir)

	stratified = StratifiedKFoldCrossValidation(a, 30, 4);

ardından yukarıda belirtilen getTrainFold ve getTestFold metodları ile sırasıyla i. eğitim
ve test kümeleri elde edilebilir. 

