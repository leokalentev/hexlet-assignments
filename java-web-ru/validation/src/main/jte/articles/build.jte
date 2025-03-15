@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@if (page.getErrors() != null)
    <ul>
        @for (var errors : page.getErrors().values())
            @for (var error : errors)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <div>
        <label>
            Название статьи
            <input type="text" name="title" value="${page.getTitle()}" />
        </label>
    </div>
    <div>
        <label>
            Контент
            <textarea name="content">${page.getContent()}</textarea>
        </label>
    </div>
    <button type="submit">Создать статью</button>
</form>
