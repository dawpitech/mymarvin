folder('Tools') {
    description('Folder for miscellaneous tools.')
}

freeStyleJob('Tools/clone-repository') {
    parameters {
        stringParam('GIT_REPOSITORY_URL', null, 'Git URL of the repository to clone')
    }
    steps {
        shell('git clone ${GIT_REPOSITORY_URL}')
    }
    wrappers {
        preBuildCleanup()
    }
}

