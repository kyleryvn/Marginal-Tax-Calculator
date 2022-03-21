# Marginal Tax Calculator
Use this calculator to get an estimate of federal and state taxes owed.
Federal taxes are calculated based on income and filing status, following formula given below. State taxes follow the same formula, given different tax rates per state. See states below for tax brackets of each state.

## Setup
To run this project locally, clone this repository to your machine:
```
$ cd ../marginal-tax-calculator
$ git clone https://github.com/kyleryvn/marginal-tax-calculator
```

## Technologies
Project is created with:

[![Java](https://img.shields.io/badge/java-jdk%2017-007396?logo=java&style=for-the-badge)](https://jdk.java.net/17/)
[![Maven](https://img.shields.io/badge/maven-3.8.4-C71A36?logo=apache%20maven&style=for-the-badge)](https://maven.apache.org/)
[![Gson](https://img.shields.io/badge/gson-2.9.0-4285F4?logo=google&style=for-the-badge&logoColor=white)](https://github.com/google/gson)
[![JUnit](https://img.shields.io/badge/junit-5.8.2-25A162?logo=junit5&style=for-the-badge&logoColor=white)](https://junit.org/junit5/)

## 2021 Federal Tax Brackets
For taxes due April 2022 or October 2022 with an extension.
<table>
    <thead align="center">
        <tr>
            <td>
                Tax Rate
            </td>
            <td>
                Single
            </td>
            <td>
                Married Filing Jointly
            </td>
            <td>
                Married Filing Separately
            </td>
            <td>
                Head of Household
            </td>
        </tr>
    </thead>
    <tbody align="center">
        <tr>
            <td>
                10%
            </td>
            <td>
                $0 - $9,950
            </td>
            <td>
                $0 - $20,550
            </td>
            <td>
                $0 - $9,950
            </td>
            <td>
                $0 - $14,650
            </td>
        </tr>
        <tr>
            <td>
                12%
            </td>
            <td>
                $9,950 - $40,525
            </td>
            <td>
                $20,550 - $83,550
            </td>
            <td>
                $9,950 - $40,525
            </td>
            <td>
                $14,650 - $55,900
            </td>
        </tr>
        <tr>
            <td>
                22%
            </td>
            <td>
                $40,525 - $86,375
            </td>
            <td>
                $83,550 - $178,150
            </td>
            <td>
                $40,525 - $86,375
            </td>
            <td>
                $55,900 - $89,050
            </td>
        </tr>
        <tr>
            <td>
                24%
            </td>
            <td>
                $86,375 - $164,925
            </td>
            <td>
                $178,150 - $340,100
            </td>
            <td>
                $86,375 - $164,925
            </td>
            <td>
                $89,050 - $170,050
            </td>
        </tr>
        <tr>
            <td>
                32%
            </td>
            <td>
                $164,925 - $209,425
            </td>
            <td>
                $340,100 - $431,900
            </td>
            <td>
                $164,925 - $209,425
            </td>
            <td>
                $170,050 - $215,950
            </td>
        </tr>
        <tr>
            <td>
                35%
            </td>
            <td>
                $209,425 - $523,600
            </td>
            <td>
                $431,900 - $647,850
            </td>
            <td>
                $209,425 - $314,150
            </td>
            <td>
                $215,950 - $539,900
            </td>
        </tr>
        <tr>
            <td>
                37%
            </td>
            <td>
                > $523,600
            </td>
            <td>
                > $647,850
            </td>
            <td>
                > $314,150
            </td>
            <td>
                > $539,900
            </td>
        </tr>
    </tbody>
</table>

When determining which tax bracket to use, a taxpayer should first calculate their taxable income, including earned and 
investment income minus adjustments and deductions.

### Example of Tax Brackets
* Single filers with less than $9,950 in taxable income are subject to a 10% income tax rate (the lowest bracket)
* Single filers who earn more than $9,950 will have the first $9,950 taxed at 10%, but earnings beyond the first bracket and up to $40,525 will be taxed at a 12% rate (the next bracket)
* Earnings from $40,526 to $86,375 are taxed at 22%, the third bracket.

Consider the following tax responsibility for a single filer with a taxable income of $50,000 in 2021:
* The first $9,950 is taxed at 10%: $9,950 × 0.10 = $995
* Then $9,951 to $40,525, or $30,574, is taxed at 12%: $30,574 × 0.12 = $3,669
* Finally, the top $9,476 (what’s left of the $50,000 income) is taxed at 22%: $9,476 × 0.22 = $2,085

Add the taxes owed in each of the brackets:
* Total taxes: $995 + $3,668 + $2,085 = $6,748

The individual’s effective tax rate is approximately 13.5% of income:
* Divide total taxes by annual earnings: $6,748 ÷ $50,000 = 0.135
* Multiply 0.135 by 100 to convert to a percentage, which yields 13.5%

Source: [Investopedia](https://www.investopedia.com/terms/t/taxbracket.asp)

## 2021 Self-Employment Tax
Self-employment tax is the imposed tax that a small business owner must pay to the federal government to fund Medicare and Social Security, similar to FICA taxes paid by an employer.
Self-employment tax is due when: 
* An individual has net earnings of $400 or more in self-employment income over the course of the tax year.
* An individual has net earnings of $108.28 or more from a tax-exempt church.

Self-employed people who make less than these thresholds from self-employment don’t have to pay any tax.

The self-employment tax rate is 15.3% of net earnings.
That rate is the sum of a 12.4% Social Security tax and a 2.9% Medicare tax on net earnings.
***Self-employment tax is not the same as income tax!***

For 2021, the Social Security wage base is $142,800. This means that in 2021, Social Security tax only applies to the first $142,800 
of your earned income from wages and self-employment. After that, you aren’t charged any additional Social Security tax. 
There is no limit on the Medicare portion of self-employment tax. So no matter how much you earn, the Medicare tax applies to all of your wages and self-employment income.

Individuals typically pay self-employment tax on 92.35% of their net earnings, not 100%. Why?
Because the 7.65% deduction takes into account the employer-half of your FICA taxes, which the business would deduct if you were paid as an employee.

High-earning tax filers are also responsible for paying an additional Medicare tax of 0.9% on income above the following thresholds, depending on their filing status:
* Married filing jointly: $250,000
* Married filing separately: $125,000
* All other filing statuses: $200,000

When filing their 2021 income tax return, self-employed individuals can claim an above-the-line deduction for half of their self-employment tax.
In effect, they get a deduction on the "employer" portion (6.2% Social Security + 1.45% Medicare = 7.65%) of their self-employment tax.

### Example of Self-Employment Tax
#### Example 1:
For example, say you have a full-time job earning $150,000 for the tax year. You also have a side hustle making custom party cakes that brings in an additional $20,000 per year.
In 2021, Since you’ve already reached the Social Security wage base, you wouldn’t have to pay the 12.4% Social Security portion of self-employment taxes on your side hustle income. You’d only have to pay the 2.9% Medicare portion of self-employment tax.

#### Example 2:
Let’s say your net income from self-employment in 2021 is $100,000. To find the taxable amount, multiply $100,000 by 92.35%.
Then, multiply your self-employment taxable income by the 15.3% self-employment tax rate.
* $92,350 x .153 = $14,130

Your self-employment taxes are $14,130 (rounded).

#### Example 3:
Robin, who runs a human resources consulting business, calculates their total net income for 2020 to be $200,000 after business expenses have been deducted. Their self-employment tax will be assessed on 92.35% * $200,000 = $184,700.
This amount is above the capped limit for the Social Security portion of the self-employment tax. Therefore, Robin's self-employment tax bill will be:
* (12.4% * $142,800 = $17,707.20) + (2.9% * $184,700 = $5,356.30) = $23,063.50

Robin decides to claim an above the line deduction for half of their self-employment tax, bringing their tax due to $11,215.55.

Sources: [Investopedia](https://www.investopedia.com/terms/s/selfemploymenttax.asp), [Nerdwallet](https://www.nerdwallet.com/article/taxes/self-employment-tax), 
[Bench](https://bench.co/blog/tax-tips/self-employment-tax/)

## 2021 State Tax Brackets
Each state carries its own tax laws. Provided below are links to the different tax brackets of all the states that collect income
taxes. Currently, Alaska, Florida, Nevada, New Hampshire**, South Dakota, Tennessee, Texas, Washington***, and Wyoming do not
collect income tax.

** New Hampshire taxes 5% on interest and dividends only<br>
*** Washington taxes 7% on capital gains income only

* [Alabama](https://www.incometaxpro.net/tax-rates/alabama.htm)
* [Arizona](https://www.incometaxpro.net/tax-rates/arizona.htm)
* [Arkansas](https://www.incometaxpro.net/tax-rates/arkansas.htm)
* [California](https://www.incometaxpro.net/tax-rates/california.htm)
* [Colorado](https://www.incometaxpro.net/tax-rates/colorado.htm)
* [Connecticut](https://www.incometaxpro.net/tax-rates/connecticut.htm)
* [Delaware](https://www.incometaxpro.net/tax-rates/delaware.htm)
* [Georgia](https://www.incometaxpro.net/tax-rates/georgia.htm)
* [Hawaii](https://www.incometaxpro.net/tax-rates/hawaii.htm)
* [Idaho](https://www.incometaxpro.net/tax-rates/idaho.htm)
* [Illinois](https://www.incometaxpro.net/tax-rates/illinois.htm)
* [Iowa](https://www.incometaxpro.net/tax-rates/iowa.htm)
* [Kansas](https://www.incometaxpro.net/tax-rates/kansas.htm)
* [Kentucky](https://www.incometaxpro.net/tax-rates/kentucky.htm)
* [Louisiana](https://www.incometaxpro.net/tax-rates/louisiana.htm)
* [Maine](https://www.incometaxpro.net/tax-rates/maine.htm)
* [Maryland](https://www.incometaxpro.net/tax-rates/maryland.htm)
* [Massachusetts](https://www.incometaxpro.net/tax-rates/massachusetts.htm)
* [Michigan](https://www.incometaxpro.net/tax-rates/michigan.htm)
* [Minnesota](https://www.incometaxpro.net/tax-rates/minnesota.htm)
* [Mississippi](https://www.incometaxpro.net/tax-rates/mississippi.htm)
* [Missouri](https://www.incometaxpro.net/tax-rates/missouri.htm)
* [Montana](https://www.incometaxpro.net/tax-rates/montana.htm)
* [Nebraska](https://www.incometaxpro.net/tax-rates/nebraska.htm)
* [New Jersey](https://www.incometaxpro.net/tax-rates/new-jersey.htm)
* [New Mexico](https://www.incometaxpro.net/tax-rates/new-mexico.htm)
* [New York](https://www.incometaxpro.net/tax-rates/new-york.htm)
* [North Carolina](https://www.incometaxpro.net/tax-rates/north-carolina.htm)
* [North Dakota](https://www.incometaxpro.net/tax-rates/north-dakota.htm)
* [Ohio](https://www.incometaxpro.net/tax-rates/ohio.htm)
* [Oklahoma](https://www.incometaxpro.net/tax-rates/oklahoma.htm)
* [Oregon](https://www.incometaxpro.net/tax-rates/oregon.htm)
* [Pennsylvania](https://www.incometaxpro.net/tax-rates/pennsylvania.htm)
* [Rhode Island](https://www.incometaxpro.net/tax-rates/rhode-island.htm)
* [South Carolina](https://www.incometaxpro.net/tax-rates/south-carolina.htm)
* [Utah](https://www.incometaxpro.net/tax-rates/utah.htm)
* [Vermont](https://www.incometaxpro.net/tax-rates/vermont.htm)
* [Virginia](https://www.incometaxpro.net/tax-rates/virginia.htm)
* [Washington DC](https://www.incometaxpro.net/tax-rates/washington-dc.htm)
* [West Virginia](https://www.incometaxpro.net/tax-rates/west-virginia.htm)
* [Wisconsin](https://www.incometaxpro.net/tax-rates/wisconsin.htm)

Source: [IncomeTaxPro](https://www.incometaxpro.net)
