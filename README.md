# ProjectGenerator

A simple project generator for when you want to work on a programming problem
on Kattis or another code site of you choice. It currently generates only .java
and C++ template files.

## Usage

Just execute the generator with the following command:

`java -jar ProjectGenerator.jar <project name> <args>`

Then cd to the directory \<project name\> and hack away!

When you are ready to compile and test, just run the command `make`.

### Arguments

* -h | -help : help menu
* -s | -size \<example file amount\> : the amount of example files to create. Defaults to 2
* -t | -template \<template\> : the template to use. Defaults to java_default
* -kattis : fetches the problems from Kattis and creates a makefile and test script for them
* -local : fetches nothing and creates empty example files

### Templates

* java_default - this is the default if no template is specified
* cpp_default - the default C++ template

### Examples

#### Generates a Java project from Kattis called Acm:

`java -jar ProjectGenerator.jar Acm -kattis`

#### Generates a C++ project from Kattis called Acm:

`java -jar ProjectGenerator.jar Acm -kattis -t cpp_default`

#### Generates a Java project locally with 3 example input files:

`java -jar ProjectGenerator.jar Acm -s 3`