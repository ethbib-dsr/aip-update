#!/bin/bash

#iterate over all other lib
current_home=`pwd`
CLIENTLIBS=`ls -R1 ${current_home}/lib/*.jar`

for i in ${CLIENTLIBS}; do  
   if [ "${CLIENTLIBS}" ] ; then
      LIBPATH=${LIBPATH}:"$i"
   fi
done

#also add conf folder to have log4j config files available
LIBPATH=${LIBPATH}:${current_home}/conf/

#set correct locales
export LANG="de_DE.UTF-8"
export LC_CTYPE="de_DE.UTF-8" 
export LC_NUMERIC="de_DE.UTF-8" 
export LC_TIME="de_DE.UTF-8" 
export LC_COLLATE="de_DE.UTF-8" 
export LC_MONETARY="de_DE.UTF-8" 
export LC_MESSAGES="de_DE.UTF-8" 
export LC_PAPER="de_DE.UTF-8" 
export LC_NAME="de_DE.UTF-8" 
export LC_ADDRESS="de_DE.UTF-8" 
export LC_TELEPHONE="de_DE.UTF-8" 
export LC_MEASUREMENT="de_DE.UTF-8" 
export LC_IDENTIFICATION="de_DE.UTF-8" 
export LC_ALL="de_DE.UTF-8" 

#start java application with correct class path and 'config.properties' location as argument
java -cp "${current_home}/bin$LIBPATH" -Xms128m -Xmx1024m com.ethz.aipupdate.AppStarter "/conf/config.test.properties"
#absolute path for server needed
#/exlibris/dps/d4_1/product/local/java/bin/java -cp "${current_home}/bin$LIBPATH" -Xms128m -Xmx2048m com.ethz.aipupdate.AppStarter "/conf/config.properties"

exit
