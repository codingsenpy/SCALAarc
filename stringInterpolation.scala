s Interpolator:                                                      |
                                                                     | 
Use it to embed variables directly in strings.                       |
Example: You can write s"Hello, $name!" to include the value of name.|
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

f Interpolator:

Allows formatted strings using specific format specifiers.
Example: You can write f"Pi is approximately $pi%.2f" to format the value of pi to two decimal places.


raw Interpolator:

Treats strings literally, meaning escape characters are not processed.
Example: Writing raw"Hello\nWorld" will display Hello\nWorld as is, without creating a new line.
