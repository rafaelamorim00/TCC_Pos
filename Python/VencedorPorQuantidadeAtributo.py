# -*- coding: utf-8 -*-
"""
Created on Fri May  1 10:14:04 2020

@author: Rafael
"""

import numpy as np
import pandas as pd
from datetime import datetime  
from datetime import date
from datetime import timedelta 
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import confusion_matrix, accuracy_score

# colunas
MAX = 114
MAIOR_STR = 115
MAIOR_AGI = 116
MAIOR_INT = 117
CLASSE = 118

partidas = pd.read_csv('picks_data.csv')
herois = pd.read_csv('herois.csv', index_col ="id")
times = pd.DataFrame(np.zeros((partidas.shape[0]*2, CLASSE+1)))

# Retorna 1 se o time vencedor tem mais str_base que o perdedor, retorna 2 se o perdedor possuir mais e 0 se forem valores iguais
def getMaiorPorAtributo(l, coluna):
    win = 0
    lose = 0
    for c in range(0, 5):
        lose += herois.loc[partidas.iloc[l,c]][coluna]
    for c in range(5, 10):
        win += herois.loc[partidas.iloc[l,c]][coluna]
        
    if (win > lose):
        return 1
    elif (win < lose):
        return 2
    else:
        return 0
    
i = 0
for l in range(0,len(partidas)-1):
    if (l % 100 == 0):
        print(l)
    maior_str = getMaiorPorAtributo(l, 'base_str')
    maior_agi = getMaiorPorAtributo(l, 'base_agi')
    maior_int = getMaiorPorAtributo(l, 'base_int')
    
    for c in range(0, 5):
        times.iloc[i, partidas.iloc[l,c]] = 1
    times.iloc[i, CLASSE] = -1
    times.iloc[i, MAIOR_STR] = (maior_str == 2)
    times.iloc[i, MAIOR_AGI] = (maior_agi == 2)
    times.iloc[i, MAIOR_INT] = (maior_int == 2)
    
    i += 1
    
    for c in range(5, 10):
        times.iloc[i, partidas.iloc[l,c]] = 1
    times.iloc[i, CLASSE] = 1
    times.iloc[i, MAIOR_STR] = (maior_str == 1)
    times.iloc[i, MAIOR_AGI] = (maior_agi == 1)
    times.iloc[i, MAIOR_INT] = (maior_int == 1)
        
    i += 1

#previsores = times.iloc[:,MAX+1:CLASSE].values    
    
    
previsores = times.iloc[:,0:CLASSE].values
classe = times.iloc[:,CLASSE].values


x_treinamento, x_teste, y_treinamento, y_teste = train_test_split(previsores, classe, test_size = 0.3, random_state = 0)

naive_bayes = GaussianNB()
naive_bayes.fit(x_treinamento, y_treinamento)

previsoes = naive_bayes.predict(x_teste)

taxa_acerto = accuracy_score(y_teste, previsoes)
taxa_erro = 1 - taxa_acerto




arvore = DecisionTreeClassifier()
arvore.fit(x_treinamento, y_treinamento)



previsoes = arvore.predict(x_teste)


confusao = confusion_matrix(y_teste, previsoes)

