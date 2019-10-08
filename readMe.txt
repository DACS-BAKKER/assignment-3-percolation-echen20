Assignment: Percolation
Student: Ethan Chen
Date Completed: 10/8/19

Notes:
I have created here a fully functional percolation graphics program (in swing and stdDraw), tester, and runtime timer
            for dynamic conductivity methods as well as several consecutive tests.
These particular files that are all fully functional are Percolation2.java, PercolationGraphics,java,
            PercolationGraphics2.java, QuickFindAndUnionTester.java, PercolationNTimer.java, and PercolationTester.java

ALL FILES:
    Percolation1: This file was my original Percolation file, and as you can see it has plenty of commented out code.
            This file works, but is also mostly where I tested out different possible algorithms and developed ways to
            connect the top to the bottom, check if each tile is full, and utilize the dynamic conductivity methods

    Percolation2: The fully working (although not perfectly efficient) Percolation method (Timed to be somewhere
            between n^3 and n^3log(n). It takes parameters n and f; n is the number of rows and columns, f is the type
            of find as follows: 0 = quick find; 1 = union find (doesn't work because the order in which child and
            parent are assigned cannot be done using my runExperiment method); 2 = weighted union find

    Percolation3: This file is more theoretical and doesn't work, but it should reduce the efficiency to n^2 or nlogn.
            This file calls all of the methods from the QuickFindAndUnionTester2 instead of me putting them into the
            file.  As well, it creates a node that represents the whole bottom row, so I only have to check if that
            single node percolates instead of every site along the bottom. This increases efficiency.

    PercolationGraphics: Creates a graphical representation of the percolation problem using java swing. Because there
            is no rectangle object in swing, I used JButtons and changed the background color to the appropriate color.
            This worked but was not ideal. As well, it prints out the percolation threshold in the console for that
            single Monte Carlo Simulation at the end.

    PercolationGraphics2: Graphical representation using stdDraw instead of java swing. Slightly easier to see the
            exact path the percolation occurs. As well, I slowed it down so that you can watch as each site is opened
            and becomes full. It also prints out the percolation threshold in the console.

    PercolationNTimer: This file was designed in order to find the exponent on the N for the efficiency. It times the
            percolation test for 100 objects, and then 200 objects and finds the exponent based on the proportional
            distance in the execution time. This utilizes the Stopwatch class and elapsedTime() method. I realized that
            the efficiency this method gives is inaccurate due to flaws in the runExperiment() method and should be
            much lower.
                *** see "addressing problems" section at bottom

    PercolationTester: This file runs the percolation many times (as many as are denoted by int value monteCarloTests)
            and prints out the average percolation threshold over all the tests. The results are consistent with what
            was said in the canvas file: a percolation threshold ~ 0.593

    QuickFindAndUnionTester: The set of Quick Find, Quick Union, and Weighted Union Find methods. This file also
            contains commented test code at the bottom to confirm they work.

    QuickFindAndUnionTester2: The same set, except with the array as a parameter for each. I did this so I could use
            the methods in Percolation without needing to copy and paste each method into percolation.

ADDRESSING PROBLEMS:
As is probably evident by the number of files I have, there were and still are some problems with the code. Some I was
able to address, and some I was not. Here are just some of the problems I experienced and my ideas for solutions:

    The Percolation N Timer is incorrect because of the runExperiment method. In this method, I run a percolation
    experiment by picking two random integers between 0 and n and opening the corresponding site. This messes with
    timing because duplicates happen frequently and add unnecessary time onto the test. The way I would address this
    is with an array list full of all of the indices of the sites. Then, I would choose a random integer between 0 and
    the length of that array list. I would choose the set of indices at that integer index of the array list, and then
    remove it from the array list. By doing this, there would never be duplicates. I was thinking of ways to do this
    with regular arrays and I could not think of a way.

    The Graphics (especially stdDraw) proved to be very difficult because I had to differentiate between the open sites
    and full sites. When you open a site, it may cause other sites to suddenly become full, so the focus has to extend
    past just the site you opened. This is why I developed the nested for loop inside the while. Every time a site is
    opened, it checks every single site again to see if it's full. It is not particularly efficient but works for the
    sake of the graphics.

    In order to switch between which find method I used, I decided to just put another parameter in the constructor.
    All it does is take an integer 0, 1, or 2 that switches between the three methods. I did not include this method
    Percolation3, but you can see this take place in Percolation2.

Overall, this project has been very challenging yet I do have a much greater understanding of the dynamic conductivity
algorithms, stdDraw, and timing algorithms.




