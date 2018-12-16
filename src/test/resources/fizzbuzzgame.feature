Feature: Generate fizz buzz series from an given one or many numbers

	Scenario: Generate fizz buzz series
	Given the user has a number and wants to generate fizz buzz series 
	When the number is given to the system
	Then the system successfully generates the fizz buzz series
	And the numbers divisible by 3 are replaced with "Fizz"
	And the numbers divisible by 5 are replaced with "Buzz"
	And the numbers divisible by 3 and 5 are replaced with "Fizz Buzz"