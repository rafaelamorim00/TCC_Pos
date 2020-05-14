# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 10:57:28 2020

@author: Rafael
"""


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

partidas = pd.read_csv('picks_data.csv')
herois = pd.read_csv('herois.csv', index_col ="id")

hpd = 0 #92
hnpd = 0 #26
for h in range(0,len(herois)-1):
    if (herois.iloc[h]['roles/0'] == 'Disabler' or herois.iloc[h]['roles/1'] == 'Disabler' or herois.iloc[h]['roles/2'] == 'Disabler'):
        hpd += 1
    else:
        hnpd += 1

def getVencedor(times):
    
    vencedorPossuiDisabler = False
    perdedorPossuiDisabler = False
        
    for h in range(0, 5):
        if herois.loc[times.iloc[h]]['roles/0'] == 'Disabler' or herois.loc[times.iloc[h]]['roles/1'] == 'Disabler' or herois.loc[times.iloc[h]]['roles/2'] == 'Disabler':
            perdedorPossuiDisabler = True
                
    for h in range(5, 10):
       if herois.loc[times.iloc[h]]['roles/0'] == 'Disabler' or herois.loc[times.iloc[h]]['roles/1'] == 'Disabler' or herois.loc[times.iloc[h]]['roles/2'] == 'Disabler':
            vencedorPossuiDisabler = True
                    
    if (vencedorPossuiDisabler and not perdedorPossuiDisabler):
        return 1
    elif (not vencedorPossuiDisabler and perdedorPossuiDisabler):
        return 0
    elif vencedorPossuiDisabler and perdedorPossuiDisabler:
        return 2
    else:
        return -1
    
disablerVencedor = 0
disablerPerdedor = 0
ambosPossuemDisabler = 0
nenhumPossuiDisabler = 0

for p in range(0,len(partidas)-1):
    resultado = getVencedor(partidas.iloc[p])
    if resultado == 1:
        disablerVencedor += 1
    elif resultado == 0:
        disablerPerdedor += 1
    elif resultado == 2:
        ambosPossuemDisabler += 1
    else: 
        nenhumPossuiDisabler += 1
        
        
        
plt.rcdefaults()
fig, ax = plt.subplots(figsize=(5, 3))

        
labels = ['Ambos', 'Apenas Vencedores', 'Apenas Perdedores', 'Nenhum']
y_pos = np.arange(len(labels))
quantidades = [ambosPossuemDisabler, disablerVencedor, disablerPerdedor, nenhumPossuiDisabler]

ax.barh(y_pos, quantidades, align='center')
ax.set_yticks(y_pos)
ax.set_yticklabels(labels)
ax.invert_yaxis()  # labels read top-to-bottom
ax.spines['right'].set_visible(False)


for v in range(0,len(quantidades)):
    ax.text(y=v, x=quantidades[v]+1000, s=quantidades[v])
