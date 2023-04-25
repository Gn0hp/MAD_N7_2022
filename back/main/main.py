from sklearn import linear_model
from sklearn.model_selection import cross_val_score
import numpy as np
import pandas as pd
import io
import gzip
from sklearn.neighbors import KNeighborsClassifier

df = pd.read_csv("./main/diabetes.csv")

df[['Glucose','BloodPressure','SkinThickness',
 'Insulin','BMI','DiabetesPedigreeFunction','Age']] = \
 df[['Glucose','BloodPressure','SkinThickness',
 'Insulin','BMI','DiabetesPedigreeFunction','Age']].replace(0,np.NaN)

df.fillna(df.mean(), inplace = True)

corr = df.corr()

from sklearn import linear_model
from sklearn.model_selection import cross_val_score
#---features---
X = df[['Glucose','BMI','Age']]
#---label---
y = df.iloc[:,8]
log_regress = linear_model.LogisticRegression()
log_regress_score = cross_val_score(log_regress, X, y, cv=10, 
scoring='accuracy').mean()

knn = KNeighborsClassifier(n_neighbors=19)
knn.fit(X, y)


Glucose = 10
BMI = 70
Age = 50
prediction = knn.predict([[Glucose, BMI, Age]])
print(prediction)

def predict_ct(age,bmi,glucose):
    prediction = knn.predict([[glucose, bmi, age]])
    return prediction[0]