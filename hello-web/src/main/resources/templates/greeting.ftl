<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white shadow-lg rounded-lg p-8 max-w-md text-center">
        <h1 class="text-4xl font-bold text-blue-600 mb-4">Hello, ${name}!</h1>
        <p class="text-gray-700 text-lg mb-6">Welcome to the dynamic greeting page.</p>
        <a href="/hello" class="text-white bg-blue-500 hover:bg-blue-600 px-4 py-2 rounded-lg transition">
            Back
        </a>
    </div>
</body>
</html>
