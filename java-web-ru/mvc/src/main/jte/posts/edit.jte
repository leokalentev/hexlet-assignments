@import exercise.dto.posts.EditPostPage
@param EditPostPage page

@if (page.getErrors() != null)
  <ul>
    @for (var validator : page.getErrors().values())
      @for (var erorr : validator)
        <li>${erorr.getMessage()}</li>
      @endfor
    @endfor
  </ul>
@endif

<form action="/posts/${page.getPost().getId()}" method="post">
  <div>
    <label>
      Название поста
      <input type="text" name="name" value="${page.getPost().getName()}"/>
    </label>
  </div>
  <div>
    <label>
      Пост
      <textarea name="body">${page.getPost().getBody()}</textarea>
    </label>
  </div>
<input type="submit" value="Редактировать"/>
</form>