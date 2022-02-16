Goal:
Create console CRUD application witch has next entities:

Writer (id, firstName, lastName, List<Post> posts)
Post (id, content, created, updated, List<Label> labels)
Label (id, name)
PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)

Store data in files:
writers.json, posts.json, labels.json

For start:
Run static method Main() of MainController