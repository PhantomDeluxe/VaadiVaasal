<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu App</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            line-height: 1.6;
            background-color: #efe8e8;
            color: #333;
        }

        header {
            background: #2d3436;
            color: #df7d7d;
            padding: 1rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header .logo {
            display: flex;
            align-items: stretch;
        }


        header .logo img {
            max-block-size: 50px; /* Increased logo size */
            height: 100vh; /* Full viewport height */
            width: auto; /* Maintain aspect ratio */
            margin-right: 1.5rem; /* Adjust spacing for alignment */
        }


        header h1 {
            font-size: 1.8rem;
            font-weight: 600;
        }

        header nav a {
            color: rgba(251, 247, 247, 0.5);
            text-decoration: none;
            margin-left: 1.5rem;
            font-size: 1rem;
            transition: color 0.3s ease;
        }

        header nav a:hover {
            color: #74b9ff;
        }

        .hero {
            background: url('https://source.unsplash.com/1920x1080/?bull,taming') no-repeat center center/cover;
            height: 70vh;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            color: #000000;
        }

        .hero h2 {
            font-size: 3rem;
            font-weight: 600;
        }

        .hero p {
            font-size: 1.2rem;
            margin-top: 0.5rem;
        }

        .floating-bull {
            position: absolute;
            bottom: 2%;
            left: 48%;
            transform: translateX(-50%);
            max-width: 750px;
            animation: float 3s ease-in-out infinite;
            opacity: 0.6; /* Set transparency */
        }

        @keyframes float {
            0%, 100% {
                transform: translateX(-50%) translateY(0);
            }
            50% {
                transform: translateX(-50%) translateY(-10px);
            }
        }

        .content {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 1rem;
        }

        .content h2 {
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 1rem;
            color: #2d3436;
        }

        .content p {
            font-size: 1rem;
            margin-bottom: 1.5rem;
        }

        .features {
            display: flex;
            flex-wrap: wrap;
            gap: 1rem;
            margin-top: 2rem;
        }

        .feature {
            flex: 1;
            min-width: 300px;
            background: #aba6e6;
            border: 1px solid #0e2ce6;
            border-radius: 8px;
            padding: 1.5rem;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .feature:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        .feature a {
            text-decoration: none;
            color: #2d3436;
            font-weight: 600;
            display: block;
            margin-top: 1rem;
            transition: color 0.3s ease;
        }

        .feature a:hover {
            color: #74b9ff;
        }

        footer {
            text-align: center;
            background: #2d3436;
            color: #534545;
            padding: 1rem 0;
            margin-top: 2rem;
        }

        footer p {
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
<header>
    <div class="logo">
        <img src="Vadivaasal_logo-removebg-preview.png" alt="Jallikattu Logo">
    </div>
    <nav>
        <a href="#about">About</a>
        <a href="#features">Features</a>
    </nav>
</header>

<div class="hero">
    <div>
        <h2>Experience the Thrill of Jallikattu</h2>
        <p>Track players, matches, and bulls like never before.</p>
        <img src="Bull-PNG-Image.png" alt="Bull" class="floating-bull">
    </div>
</div>

<div class="content" id="about">
    <h2>About Jallikattu</h2>
    <p>Jallikattu is a traditional bull-taming sport celebrated during Pongal in Tamil Nadu. The game highlights bravery, agility, and the bond between humans and animals. Our app brings this rich tradition to the digital world, offering a platform to track player rankings, match details, and bull statistics.</p>
</div>

<div class="content" id="features">
    <h2>App Features</h2>
    <div class="features">
        <div class="feature">
            <h3>Player & Rankings</h3>
            <p>Track the performance and rankings of participants across events.</p>
            <a href="player_rankings.jsp">Learn More</a>
        </div>
        <div class="feature">
            <h3>Matches & Scoreboard</h3>
            <p>Get real-time updates on matches and detailed scoreboards.</p>
            <a href="Matches_and_Scoreboard.jsp">Learn More</a>
        </div>
        <div class="feature">
            <h3>Bulls & Tamers</h3>
            <p>Explore profiles of bulls and their tamers, including stats and history.</p>
            <a href="Bulls_tamers.jsp">Learn More</a>
        </div>
    </div>
</div>

<footer>
    <p>&copy; 2025 Jallikattu App. All Rights Reserved.</p>
</footer>
</body>
</html>


