from scipy import sparse
from sklearn.svm import SVR
from sklearn.externals import joblib

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
	
trainingDataset,trainingoutputs = getDataset('FileName_training.csv')

svr_rbf = SVR(kernel='rbf', C=1e3, gamma=0.1)
print(svr_rbf.get_params())
print('Training Started....')
train_rbf = svr_rbf.fit(trainingDataset,trainingoutputs)
joblib.dump(train_rbf,'Model.pkl')


'''svr_poly = SVR(kernel='poly', C=1e3, degree=2)
print(svr_poly.get_params())
print('Training Started....')
train_poly = svr_poly.fit(trainingDataset,trainingoutputs)
joblib.dump(train_poly,'Model.pkl')'''


print('Model Saved....')