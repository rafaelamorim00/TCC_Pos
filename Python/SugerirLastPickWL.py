# -*- coding: utf-8 -*-
"""
Created on Thu Apr  2 19:39:44 2020

@author: Rafael
"""

import numpy as np
import pandas as pd
from sklearn import tree
from sklearn.naive_bayes import GaussianNB
from sklearn.model_selection import train_test_split
import graphviz
import os
os.environ["PATH"] += os.pathsep + 'D:/Desenvolvimento/Pós/graphviz/bin/'

df = pd.read_csv('ambosML.csv')
df = df.dropna()

df['L1'] = df['L1'].astype(int)
df['L2'] = df['L2'].astype(int)
df['L3'] = df['L3'].astype(int)
df['L4'] = df['L4'].astype(int)
df['L5'] = df['L5'].astype(int)
df['W1'] = df['W1'].astype(int)
df['W2'] = df['W2'].astype(int)
df['W3'] = df['W3'].astype(int)
df['W4'] = df['W4'].astype(int)
df['W5'] = df['W5'].astype(int)

x = df[['L1', 'L2', 'L3', 'L4', 'L5', 'W1', 'W2', 'W3', 'W4']]
y = df['W5']

clf = tree.DecisionTreeClassifier(criterion='entropy')
clf = clf.fit(x, y)

clf.predict([[5, 68, 25, 44, 102,
              26, 53, 99, 54]])


previsores = x
classe = y

x_treinamento, x_teste, y_treinamento, y_teste = train_test_split(x,
                                                                  y,
                                                                  test_size = 0.3,
                                                                  random_state = 0)

naive_bayes = GaussianNB()
naive_bayes.fit(x_treinamento, y_treinamento)