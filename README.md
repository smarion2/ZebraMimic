# Zebra Mimic

Emulate printing for a Zebra RFID printer to run on a pine64 (or any tcp device for that matter)

Command line options:

| Flag             | Description |
| ---------------- |:-----------:|
|-help             | Display help file |
|-i                | Create png images of the tags printed |
|-l                | Create log file of tags printed |
| -p `<Port Number>` |  Set port number for printer (default 9100) |
| -t `<quantity>`    |  Simulate paper out with given quantity of tags |
| -v `<chance>`      |  Simulate tag voids with a given percent chance. |


Only dependancy is Aparche Commons CLI for command line options
https://commons.apache.org/proper/commons-cli/index.html
