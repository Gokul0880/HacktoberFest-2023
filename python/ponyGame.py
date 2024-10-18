import turtle

# Set up the screen
screen = turtle.Screen()
screen.title("Pong Game")
screen.bgcolor("black")
screen.setup(width=800, height=600)
screen.tracer(0)

# Left Paddle
left_paddle = turtle.Turtle()
left_paddle.shape("square")
left_paddle.color("white")
left_paddle.shapesize(stretch_wid=6, stretch_len=1)
left_paddle.penup()
left_paddle.goto(-350, 0)

# Right Paddle
right_paddle = turtle.Turtle()
right_paddle.shape("square")
right_paddle.color("white")
right_paddle.shapesize(stretch_wid=6, stretch_len=1)
right_paddle.penup()
right_paddle.goto(350, 0)

# Ball
ball = turtle.Turtle()
ball.shape("square")
ball.color("white")
ball.penup()
ball.goto(0, 0)
ball.dx = 0.15
ball.dy = -0.15

# Score variables
left_score = 0
right_score = 0

# Score display
score_display = turtle.Turtle()
score_display.color("white")
score_display.penup()
score_display.hideturtle()
score_display.goto(0, 260)
score_display.write("Left: 0  Right: 0", align="center", font=("Courier", 24, "normal"))

# Functions to move paddles
def left_paddle_up():
    y = left_paddle.ycor()
    if y < 250:
        y += 20
    left_paddle.sety(y)

def left_paddle_down():
    y = left_paddle.ycor()
    if y > -240:
        y -= 20
    left_paddle.sety(y)

def right_paddle_up():
    y = right_paddle.ycor()
    if y < 250:
        y += 20
    right_paddle.sety(y)

def right_paddle_down():
    y = right_paddle.ycor()
    if y > -240:
        y -= 20
    right_paddle.sety(y)

# Keyboard bindings
screen.listen()
screen.onkey(left_paddle_up, "w")
screen.onkey(left_paddle_down, "s")
screen.onkey(right_paddle_up, "Up")
screen.onkey(right_paddle_down, "Down")

# Function to update the score
def update_score():
    score_display.clear()
    score_display.write(f"Left: {left_score}  Right: {right_score}", align="center", font=("Courier", 24, "normal"))

# Main game loop
while True:
    screen.update()

    # Move the ball
    ball.setx(ball.xcor() + ball.dx)
    ball.sety(ball.ycor() + ball.dy)

    # Ball collision with top and bottom walls
    if ball.ycor() > 290:
        ball.sety(290)
        ball.dy *= -1

    if ball.ycor() < -290:
        ball.sety(-290)
        ball.dy *= -1

    # Ball collision with left and right paddles
    if (ball.dx > 0 and 340 < ball.xcor() < 350 and right_paddle.ycor() + 50 > ball.ycor() > right_paddle.ycor() - 50) or \
       (ball.dx < 0 and -350 < ball.xcor() < -340 and left_paddle.ycor() + 50 > ball.ycor() > left_paddle.ycor() - 50):
        ball.dx *= -1

    # Ball goes out of bounds
    if ball.xcor() > 390:
        left_score += 1
        update_score()
        ball.goto(0, 0)
        ball.dx *= -1

    if ball.xcor() < -390:
        right_score += 1
        update_score()
        ball.goto(0, 0)
        ball.dx *= -1

    # Delay for smooth motion
    turtle.delay(10)
