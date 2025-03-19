@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page
    <h1>${page.getPost().getName()}</h1>
    <p>${page.getPost().getBody()}</p>