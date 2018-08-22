from sklearn.externals import joblib
from scipy import sparse
from sklearn.metrics import mean_squared_error
from sklearn.metrics import explained_variance_score

def getDataset(filename):
	filehandle = open(filename,'r')
	noOfAttrs = 0
	noOfInstances = 0
	tempValues = []
	outputs = []
	row_ind = []
	col_ind = []
	flag = True
	for line in filehandle:
		if flag == True:
			details = line.split(',')
			noOfAttrs = len(details)
			flag = False
		else:
			noOfInstances += 1
			values = line.split('\n')[0]
			values = values.split(',')
			tempValues.extend(values[:noOfAttrs-1])
			outputs.append(values[noOfAttrs-1])
	for i in range(noOfInstances):
		for j in range(noOfAttrs-1):
			row_ind.append(i)
			col_ind.append(j)
	
	tempValues = list(map(float, tempValues))
	dataset = sparse.coo_matrix((tempValues, (row_ind, col_ind))).toarray()
	outputs = list(map(float, outputs))
	return dataset,outputs
	
trainingDataset,trainingoutputs = getDataset('FileName_testing.csv')
trained_rbf = joblib.load('Model.pkl')

print('Model loaded....')
Y_pred = trained_rbf.predict(trainingDataset)
print("\n\nAct. o/p         pred. o/p")
for i in range(len(trainingoutputs)):
	print(str(trainingoutputs[i])+'\t\t'+str(Y_pred[i]))
print('Mean Squared Error: '+str(mean_squared_error(trainingoutputs, Y_pred)))
print('variance: '+str(explained_variance_score(trainingoutputs, Y_pred)))