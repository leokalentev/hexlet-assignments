@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

    <h1>Список постов</h1>
             <ul>
                @for (var post : page.getPosts())
                    <li>
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                    </li>
                @endfor
             </ul>
             <div>
                @if (page.isHasLast())
                    <a href="${NamedRoutes.postsPath()}?page=${page.getPage() - 1}">Назад</a>
                @endif
                  @if (page.isHasNext())
                    <a href="${NamedRoutes.postsPath()}?page=${page.getPage() + 1}">Вперёд</a>
                @endif
             </div>
