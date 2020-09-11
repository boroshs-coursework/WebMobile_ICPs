document.getElementById("rock").addEventListener('click', function(){playGame(0);});
document.getElementById("paper").addEventListener('click', function(){playGame(1);});
document.getElementById("scissors").addEventListener('click', function(){playGame(2);});

function getRand(){ // returns rand int 0,1,or 2
    return Math.floor(Math.random() * 3);
}

function getWord(val){ // returns word for int 0,1,2
    var str = "";
    if (val == 0) {
        str = "ROCK";
    } else if (val == 1) {
        str = "PAPER";
    } else if (val == 2) {
        str = "SCISSORS";
    }
    return str;
}

function playGame(you){ // changes text to rock,paper,scissors and win,lose,tie
    var player = document.getElementById("player");
    var opponent = document.getElementById("opponent");
    var winner = document.getElementById("winner");

    var opp = getRand();
    opponent.innerHTML = `Opponent Picked ${getWord(opp)}!`;
    player.innerHTML = `You Picked ${getWord(you)}!`;

    if (you === opp) {
        winner.innerHTML='You TIE!';
    }

        // 0 vs 1,2 means true-lose,true-win
        // 1 vs 0,2 means false-win,true-lose
        // 2 vs 0,1 means false-lose,false-win
    else if (you < opp) { // TRUE you < opp
        if (you+opp === 2){ // true-win
            winner.innerHTML='You WIN!';
        }
        else{ // true-lose
            winner.innerHTML='You LOSE!';
        }
    } else { // FALSE you < opp
        // 0 vs 1,2 means true-lose,true-win
        // 1 vs 0,2 means false-win,true-lose
        // 2 vs 0,1 means false-lose,false-win
        if (you+opp === 2){ // false-lose
            winner.innerHTML='You LOSE!';
        }
        else{ // false-win
            winner.innerHTML='You WIN!';
        }
    }
}