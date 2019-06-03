import sys


def dict_data():
    temp = {0: '000001100000',
            1: '000001100000',
            2: '000001100000',
            3: '000001100000',
            4: '000001100000',
            5: '000011010000',
            6: '000011010000',
            7: '000011000000',
            8: '000011000001',
            9: '011011010000',
            10: '000110010000',
            11: '100000010000',
            12: '000001001000',
            13: '000000000100',
            14: '000000000010'}
    return temp


def dic_ALU():
    temp = {
        0: 0,
        1: 1,
        2: 7,
        3: 2,
        4: 6,
        5: 0,
        6: 7,
        7: 2,
        8: 0,
        9: 0,
        10: 0,
        11: 1,
        12: 0,
        13: 0,
        14: 0,
    }
    return temp


def getting_signals(tdictionary, opp):
    for keys in tdictionary:
        if keys == opp:
            return tdictionary[keys]


temp_in = sys.argv
oppcode = int(temp_in[1])
dictionary = {}
dictionary = dict_data()
ALUdictionary = dic_ALU()
for i in getting_signals(dictionary, oppcode):
    print(i, end=',')
print(getting_signals(ALUdictionary, oppcode))
