#ifndef _DOMINION_HELPERS_H
#define _DOMINION_HELPERS_H

#include "dominion.h"

int drawCard(int player, struct gameState *state);
int updateCoins(int player, struct gameState *state, int bonus);
int discardCard(int handPos, int currentPlayer, struct gameState *state, 
		int trashFlag);
int gainCard(int supplyPos, struct gameState *state, int toFlag, int player);
int getCost(int cardNumber);
int cardEffect(int card, int choice1, int choice2, int choice3, 
	       struct gameState *state, int handPos, int *bonus);
int adventurerFunc(int player, struct gameState *state);
int smithyFunc(int currentPlayer, struct gameState *state, int handPos);
int council_roomFunc(int currentPlayer, struct gameState *state, int handPos);
int numHandCards(struct gameState *state);

int handCard(int handNum, struct gameState *state);

#endif
