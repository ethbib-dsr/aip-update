# AIP Update #


Application that updates AIP (Archival Information Package) in Rosetta. A queue table holds all entities that should either be inserted as new SIP into Rosetta or updated an existing IE in Rosetta. All relevant files are located on an SFTP storage location and will be moved appropriately.

## Current Version 1.1.1 ##

## History ##

### version 1.1.1 ###
* added REJECTED handling
* implemented error log for error-less errors 

### version 1.1 ###
* implementation deleted items
* Deleted business domain

### version 1.0 ###
* cleanup SFTP 7 days after status AIP_FINISHED
* cleanup working dir 7 days after status AIP_FINISHED

### version 0.9 ###
* flexible webservice location

### version 0.8 ###
* update file dc

### version 0.7 ###
* definition and readout of relevant file dc mets elemtns

### version 0.6 ###
* handle RIPID
* refactoring of update process

### version 0.5 ###
* definition and readout of dc mets elemtns
* update dc

### version 0.4 ###
* extract Rosetta mets
* implement file update

### version 0.3 ###
* find records to update
* copy records to update
* implement a comparator for all files

### version 0.2 ###
* implement insert of new SIP

### version 0.1 ###
* Database connectivity
* Iterate queue table with limited number of records
* simulate insert

### version 0.0.3 ###
* singleton implementation
* database base configuration

### version 0.0.2 ###
* implementation of global logger
* properties.config
* implemenation of configuration class

### version 0.0.1 ###
* setup bitbucket repo
* setup Eclipse project
