<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>@param String title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<!-- Хедер -->
<header>
    <div class="container">
        <h1>@title</h1>
        <nav>
            <a href="/" class="text-white mr-3">Главная</a>
            <a href="/users" class="text-white">Пользователи</a>
        </nav>
    </div>
</header>

<!-- Контент -->
<main class="container mb-4">
    @content
</main>

<!-- Футер -->
<footer class="bg-dark text-white text-center p-3">
    <div class="container">
        <p>Йоу</p>
    </div>
</footer>

</body>
</html>
