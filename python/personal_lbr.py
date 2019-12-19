import personal_recommendation_func as prf
from sys import argv
def recommendation_lbr(userID):
    prf.personal_recommendation_lbr(userID)

if __name__ == '__main__':
    userId = argv[1]
    print(userId)
    recommendation_lbr(userId)
