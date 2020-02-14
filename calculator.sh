#!/usr/bin/env bash

#================================================================
# HEADER : CalculatorXML.sh
#================================================================
#% SYNOPSIS
#+    ${SCRIPT_NAME}
#+
SCRIPT_NAME=calculator.sh
#+
#% DESCRIPTION
#%    This is script converts xml files into a user friendly csv.
#%    It is intended to be used in conjucntion with CalculatorXML.jar
#%    and the Calculator3 Web Api as part of the Thunes Calculator Interview
#%    under the section Extras.
#%	
#% OPTIONS
#%
#%
#% EXAMPLES
#%    ${SCRIPT_NAME}
#%
#================================================================
#- IMPLEMENTATION
#-    version         ${SCRIPT_NAME} 0.0.1
#-    author          Mark James Kiptubei
#-    copyright       
#-    license         
#-    script_id       00001
#-
#================================================================
#  HISTORY
#     2020/02/13 : mkiptubei : Script creation
#     
#================================================================
#  REQUIRED
#	Java 8+
#	CalculatorXml.jar , style.xsl , calculator.sh
#	Create a folder called Calculator3 in {$HOME} directory.
#	Copy calculator.sh , styl.xsl and CalculatorXML.jar
#	All 3 plus source code can be can be downloaded from git:
#	https://github.com/kiptubei/CalculatorXML.git
#
#================================================================
# END_OF_HEADER
#================================================================

#


# Paths
# -----------------------------------

scriptPath="../Calculator3"
TOMCAT_PATH="http://localhost:8080"
APP_PATH="Calculator3/start/calculate"

# Arithmetic Variables
# -----------------------------------
# All variables are initialized to zero.
# Modify these according to the calculation you wish to perform
#

VALUE1=0
VALUE2=1

# Actions
# -----------------------------------
# All actions that the script can perform
# 

ADD="add/$VALUE1/$VALUE2"
SUB="sub/$VALUE1/$VALUE2"
DIV="div/$VALUE1/$VALUE2"
MUL="mul/$VALUE1/$VALUE2"
FAC="fac/$VALUE1"



# curl calculate
# -----------------------------------
# 
# This command passes the web api url to curl to perform the chosen
# action as defined above (ADD,SUB,DIV,MUL,FAC)
# Calculator 3 web api passes an xml to curl 
# You can edit the variables {$VALUE1} {$VALUE2} and actions {$ADD} to perform calculations safely 
#
# e.g
# <?xml version="1.0" encoding="UTF-8" standalone="yes"?><result><description>add</description><inputOne>4</inputOne><inputTwo>3<inputTwo><output>7.0</output></result>
#
# strips the xml tags and writes them to file x3cut.xml
#
#  #NOTE: UNCOMMENT AND MODIFY IF YOU WISH TO USE THIS AS YOU PRIMARY INTERFACE

 
#uncomment this line if you want a new empty x3cut.xml and data3.xml file
#cat xempty.xml > x3cut.xml

curl $TOMCAT_PATH/$APP_PATH/$FAC | cut -c 56- >>  x3cut.xml 
#curl $TOMCAT_PATH/$APP_PATH/$MUL | cut -c 56- >>  x3cut.xml
#curl $TOMCAT_PATH/$APP_PATH/$ADD | cut -c 56- >>  x3cut.xml 


# create result csv file
# -----------------------------------
# Here the xml created by operations are converted into a user friendly csv file for 
# easier manipulation by common desktop applictions e.g MS Office,LibreOffice suite
# 
#
#  ## NOTE: UNCOMMENT OUT THIS SECTION ONLY WHEN YOU ARE 
#  ##	   WANT TO GET THE CSV RECORD OF ALL YOU CALCULATIONS.
#  ##      OUTPUT IS IN THE PROJECT DIRECTORY WHERE Calculator3 FILES
#  ##      AND CalculatorXML.jar IS LOCATED 

# append tag to the beggining and end of the file for proper XML style formating
cp x3cut.xml data3.xml
sed -i '1i<host>' data3.xml
echo '</host>' >> data3.xml

echo  $scriptPath
java -jar $scriptPath/CalculatorXml.jar

