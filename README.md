# _____    _                 __  __ _           _      
# |__  /___| |__  _ __ __ _  |  \/  (_)_ __ ___ (_) ___ 
#   / // _ \ '_ \| '__/ _` | | |\/| | | '_ ` _ \| |/ __|
#  / /|  __/ |_) | | | (_| | | |  | | | | | | | | | (__ 
# /____\___|_.__/|_|  \__,_| |_|  |_|_|_| |_| |_|_|\___|
                                                        

Emulate printing for a Zebra RFID printer to run on a pine64 (or any tcp device for that matter)

Command line options:

| Flag             | Description |
| ---------------- |:-----------:|
|-help             | This project will attempt to emulate a zebra printer |
|-i                | Create png images of the tags printed |
|-l                | Create log file of tags printed |
| -p <Port Number> |  Set port number for printer (default 9100) |
| -t <quantity>    |  Simulate paper out with given quantity of tags |
| -v <chance>      |  Simulate tag voids with a given percent chance. |


Only dependancy is Aparche Commons CLI for command line options
https://commons.apache.org/proper/commons-cli/index.html
