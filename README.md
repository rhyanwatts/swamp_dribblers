# The Bike Sack
Raspberry Pi Project
Created for BITS SP2 by team Swamp Dribblers

The Bike Sack is a complete motorcycle computer solution. Using a Raspberry PI hardware the Java based solution manages and monitors all aspects of a motorcycle's electrical system. Information collected about the current state of the bike is output to screen to keep the rider well informed.

The Bike Sack is a motorcycle lighting and instrument system that is simple to install on any motorcycle. If you are building a custom bike, restoring an old one or just wish to upgrade your bike â€“ there is a Bike Sack model to suit you.

The Bike Sack uses a computer about the size of a wallet to control your motorcycle lights, collect information from sensors and provide feedback to the rider.

Created By
- Aidan Holmes S3355003
- Ben Denford S3573670
- Matthew Flack S3493444
- Rhyan Watts S3590266
- Steven Grueber S3502715

## Features

### Feature 1 - Command Line Input
Allow the user to provide keyboard input to the application console to trigger features to turn on or off. Also keyboard input to simulate inputs from various instruments. 
#### Validation Tests
- Keyboard shortcuts for control
- All Char input to be recognised, invalid input to be ignored and valid input acted upon.
- All state changes are to be written to command line.

### Feature 2 - Brake Lights ON/OFF
Turn a brake light on & off based on keyboard input.
#### Validation Tests
- Triggered by key press of designated brake key to turn the brake lights on.

### Feature 3 - Faded Indicators Fade IN/OUT
Turn either a left or right indicator on, the indicator fades in then out in a loop until the indaictor is turned offor the other indicator is turned on.
#### Validation Tests
- By trigger from designated indicator switch for left or right.
- Corresponding indicator light fades on over 2 seconds and fades off over 2 seconds in a loop.
- Indicator light loop will stop when the designated ‘cancel’ key is pressed.

### Feature 4 - Engine Temp with High Temp Warning
Take a value from the temperature sensor and display it to the rider. Output to include a warning at a set alarm value. 
#### Validation Tests
- System will increment or decrement stored temperature value when designated Temp+ or Temp- keys are pressed.
- System to trigger alert (via console output or graphic display) when temperature exceeds a pre-set value
- Alert to remain active until the temperature falls below the pre-set value.

### Feature 5 - Headlight LOW/HIGH Beam
Turn a headlight on and toggl between different LOW and HIGH states.
#### Validation Tests
- Default headlight state will be "Low Beam"
- Key press of designated "High Beam" key will activate High Beam indicator via console output or graphic display.
- Key press of designated "Low Beam" key will extinguish High Beam indicator via console output or graphic display

### Feature 6 - Fuel Level Low Warning
Warning is displayed to notify the rider that the fuel is running low.
#### Validation Tests
- System will increment or decrement stored fuel level value when designated Fuel+ or Fuel keys are pressed.
- System to trigger alert (via console output or graphic display) when fuel falls below a pre-set value.

## Extended Features

### Extended Feature 1 - Command Line GUI
Visually represent the application to the user. Each feature will be displayed and updated based on input from the user. Instructional captions will be included to show the user how to operate the application.
#### Validation Tests
- Relevant system variable states to be written to a text based display (console).
- Value based indications will have a numerical value as a minimum (e.g. Temp = 60*C).
- Value based indications preferably also have a proportional representation e.g Fuel = 50% [####----]
- Information displayed will include: Indicators (show left or right blinking), Engine Temperature + Temperature Warning, High Beam Indicator, Fuel Level + Fuel Warning.
	
### Extended Feature 2 - Fuel Level Gauge
Dispaly the current level of fuel in a guage and also as a  percentage. Controls to simulate the fuel going up and down.
#### Validation Tests
- Decrease Fuel to decrease percentage fuel
- Increase fuel in increase fuel percentage

### Extended Feature 3 - Engine Temp Gauge
Extend temperature gauge functionality from "F4 - Engine Temp with Warning" to include display in degrees celsius
#### Validation Tests
- Engine temperature variable manipulation per Engine temperature warning feature.
- Engine temperature to be displayed in celsius via Console text GUI.

## Group Extended Features

### Group Extended Feature 1 - Odometer
The odometer display will increment as the system receives pulse inputs from a sensor (e.g. reed switch + magnet). The increment must vary according to the wheel circumference variable.
#### Validation Tests
- The odometer value will be incremented by the value of the wheel circumference with each key press of the designated odometer + key.
- The odometer value will be displayed via Console text GUI.
	
### Group Extended Feature 2 - Tripmeter
The trip meter will advance at precisely the same rate as the Odometer. The trip meter must reset to zero following a reset command and then continue advancing at the same rate as the Odometer.
#### Validation Tests
- The Trip Meter value will be incremented by the value of the wheel circumference with each key press of the designated odometer + key.
- The Trip Meter value will be displayed via Console text GUI.
- Trip Meter value will reset on reset button press

### Group Extended Feature 3 - Fuel Usage Gauge
The fuel usage display will take information from E2-Fuel Level Gauge and GE1-Odometer to calculate fuel usage in L/100km.
#### Validation Tests
- The fuel usage value will be change when input from odometer and fuel level received
- The fuel usage value will be displayed in L/100km via Console text GUI.
