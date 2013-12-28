BackProp-Neural-Net
===================

Java program to implement a backprop neural net.
The system is fairly simple to use, simply create two comma delimited files, 
one which is used for training (which has x number of columns for inputs and y number of columns for outputs), 
and one which will contain the x inputs for which you don't have any outputs yet.  
Do not put headers on the columns of data and output as a comma delimited text file from excel.  
Build an engine using the add node buttons, 
(you will need to add the same number of input nodes and output nodes as your training data has).  
The middle (or hidden) nodes can be as many or as few as you wish, and the predictive nature of the model 
will be different depending upon how many you add.  Too few and the model will be crude, to many and the model 
will train well but not give you an output you will consider useful.  Once the engine is built and the training 
file is loaded, you can set the error threshold, and the number of iterations you which the model to be trained to.  
Once the engine is trained, you can step through the training data to see how each case is predicted.  
Next load the Results file that you which to create, and press the Use Engine button.  
Depending upon the number of data sets this can happen very fast.  
Close the results file, and reopen in excel, and you will see the results.  
I have attached a simple test case as well, there are six number with the sum of the numbers in the 7th column (1 output).  If you use this file to train, after about 500 training cycles you will see about a 10% error in the output of a result.  
After about 1000 cycles it should be much lower. 
aa is the training file, and ab is the results.
