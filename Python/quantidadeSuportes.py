# -*- coding: utf-8 -*-
"""
Created on Wed Apr 22 18:48:11 2020

@author: Rafael
"""

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

partidas = pd.read_csv('picks_data.csv')
herois = pd.read_csv('herois.csv', index_col ="id")

w0l0 = 0
w1l0 = 0
w2l0 = 0
w0l1 = 0
w1l1 = 0
w2l1 = 0
w0l2 = 0
w1l2 = 0
w2l2 = 0

def verificar(times):
    
    w = 0
    l = 0
    
    global w0l0
    global w1l0
    global w2l0
    global w0l1
    global w1l1
    global w2l1
    global w0l2
    global w1l2
    global w2l2
        
    for h in range(0, 5):
        if herois.loc[times.iloc[h]]['roles/0'] == 'Support':
            l += 1
                
    for h in range(5, 10):
       if herois.loc[times.iloc[h]]['roles/0'] == 'Support':
            w += 1
    
    if w == 0:
        if l == 0:
            w0l0 += 1
        elif l == 1: 
            w0l1 += 1
        else:
            w0l2 += 1
    elif w == 1:
        if l == 0:
            w1l0 += 1
        elif l == 1:
            w1l1 += 1
        else:
            w1l2 += 1
    else:
        if l == 0:
            w2l0 += 1
        elif l == 1:
            w2l1 += 1
        else:
            w2l2 += 1
        

for p in range(0,len(partidas)-1):
    verificar(partidas.iloc[p])
        




eixoVencedor = ['0', '1', '2+']
zeroL = [w0l0, w1l0, w2l0]
umL = [w0l1, w1l1, w2l1]
doisMaisL = [w0l2, w1l2, w2l2]

x = np.arange(len(eixoVencedor))
width = 0.2

fig, ax = plt.subplots()
rects1 = ax.bar(x - width, zeroL, width, label='0')
rects2 = ax.bar(x, umL, width, label='1')
rects3 = ax.bar(x + width, doisMaisL, width, label='2+')

# Add some text for labels, title and custom x-axis tick labels, etc.
#ax.set_ylabel('Scores')
#ax.set_title('Scores by group and gender')
ax.set_xticks(x)
ax.set_xticklabels(eixoVencedor)
ax.legend()
ax.spines['top'].set_visible(False)

def porcentagem(valor, total):
    return round((valor*100)/total, 1)

def autolabel(rects):
    """Attach a text label above each bar in *rects*, displaying its height."""
    i = 0
    total = 0
    for rect in rects:
        i += 1
        if i == 1:
            total = 1771
        elif i == 2:
            total = 24361
        else:
            total = 45304
        height = rect.get_height()
        ax.annotate(round(porcentagem(height, total), 1),
                    xy=(rect.get_x() + rect.get_width() / 2, height),
                    xytext=(0, 3),  # 3 points vertical offset
                    textcoords="offset points",
                    ha='center', va='bottom')


#autolabel(rects1)
#autolabel(rects2)
#autolabel(rects3)
autolabel(rects1)
autolabel(rects2)
autolabel(rects3)

fig.tight_layout()

plt.show()











        
