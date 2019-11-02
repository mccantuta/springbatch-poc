[![Build Status](https://travis-ci.com/mccantuta/springbatch-poc.svg?branch=master)](https://travis-ci.com/mccantuta/springbatch-poc)
[![codecov](https://codecov.io/gh/mccantuta/springbatch-poc/branch/master/graph/badge.svg)](https://codecov.io/gh/mccantuta/springbatch-poc)

# Spring Batch POC
Proof of concept of Spring Batch

This POC include some Jobs to allied Spring Batch guide principles.

Jobs included:
* Simple String Batch.

    Batch that read elements from a String List and send it to a simple writer to log console.
* Expensive Memory Resource Batch

    This batch use **ExpensiveMemoryResource** bean, that represent an object with high memory consumption.
    For example purpose the Reader creates this beans and thw Writer log to console output.
