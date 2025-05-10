@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
content = @`
    <main>
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.getPost().getName()}</h1>
        </div>

        <div class="mx-auto p-4 py-md-5">
            ${page.getPost().getBody()}
        </div>
        <!-- BEGIN -->
        <h2>
            Отредактировать данный пост
            <a href="${exercise.util.NamedRoutes.editPostPath(page.getPost().getId())}">Редактировать</a>
        </h2>
        <!-- END -->
    </main>
`
)@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
content = @`
    <main>
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.getPost().getName()}</h1>
        </div>

        <div class="mx-auto p-4 py-md-5">
            ${page.getPost().getBody()}
        </div>
        <!-- BEGIN -->
        <h2>
            Отредактировать данный пост
            <a href="${exercise.util.NamedRoutes.editPostPath(page.getPost().getId())}">Редактировать</a>
        </h2>
        <!-- END -->
    </main>
`
)