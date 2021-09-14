const robotImg = "<div class=\"img-wrapper\"><img id=\"droid\" src=\"droid.svg\" class=\"img-responsive center-block d-block mx-auto\" alt=\"Robot\"></div>";

const getRobotImg = () => document.getElementById("droid");

const directionMap = {
    0: "270deg",
    1: "0deg",
    2: "90deg",
    3: "180deg"
}

document.addEventListener("DOMContentLoaded", function(event) {
    document.getElementById("script").value =
        "POSITION 1 3 EAST //sets the initial position for the robot\n" +
        "FORWARD 3 //lets the robot do 3 steps forward\n" +
        "WAIT //lets the robot do nothing\n" +
        "TURNAROUND //lets the robot turn around\n" +
        "FORWARD 1 //lets the robot do 1 step forward\n" +
        "RIGHT //lets the robot turn right\n" +
        "FORWARD 2 //lets the robot do 2 steps forward"
});


document.addEventListener('click', function (event) {
	if (!event.target.matches('.btn')) return;
	getRobotPosition();
}, false);

const getRobotPosition = () => {
    fetch('/robot/move', {
        method: 'POST',
        body: document.getElementById('script').value,
        headers: {
          "Content-type": "text/plain"
        }
      })
      .then(response => response.json())
      .then(json => updateGrid(json))
      .catch(err => console.log(err))
}

const updateGrid = (position) => {

  // remove existing robot
  if (getRobotImg() !== null) {
    document.getElementById("droid").remove();
  }

  // add robot with new position and direction
  document.getElementById(position.yPosition + "-" + position.xPosition).innerHTML = robotImg;
  getRobotImg().setAttribute("style", "transform:rotate(" + directionMap[position.direction] + ")");
}