@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@param PostsPage page

@template.layout.page(
    content = @`
        <div class="container">
            <h1 class="my-4">Список постов</h1>

            <!-- Вывод флеш-сообщения с учетом типа -->
            @if(page.getFlash() != null)
                <div class="alert alert-${page.getFlashType() != null ? page.getFlashType() : "info"} alert-dismissible fade show" role="alert">
                    ${page.getFlash()}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            @endif

            <!-- Кнопка для создания нового поста -->
            <div class="mb-3">
                <a href="/posts/build" class="btn btn-primary">Создать новый пост</a>
            </div>

            <!-- Вывод списка постов -->
            @if(page.getPosts().isEmpty())
                <p>Постов пока нет</p>
            @else
                <div class="row">
                    @for(Post post : page.getPosts())
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${post.getName()}</h5>
                                    <p class="card-text">${post.getBody()}</p>
                                    <a href="/posts/${post.getId()}" class="btn btn-sm btn-primary">Подробнее</a>
                                </div>
                            </div>
                        </div>
                    @endfor
                </div>
            @endif
        </div>
    `
)