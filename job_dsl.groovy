def seeded_jobs_dsl = 
"""freeStyleJob(DISPLAY_NAME) {
    properties {
        githubProjectProperty {
            projectUrlStr(GITHUB_NAME)
            displayName(DISPLAY_NAME)
        }
    }
    wrappers {
        preBuildCleanup()
    }
    scm {
        github(GITHUB_NAME)
    }
    steps {
        shell('make fclean')
        shell('make')
        shell('make tests_run')
        shell('make clean')
    }
    triggers {
        scm('* * * * *')
    }
}"""

folder('Tools') {
    description('Folder for miscellaneous tools.')
}

freeStyleJob('Tools/clone-repository') {
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the repository to clone')
    }
    steps {
        shell('git clone ${GIT_REPOSITORY_URL}')
    }
    wrappers {
        preBuildCleanup()
    }
}

freeStyleJob('Tools/SEED') {
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam('DISPLAY_NAME', '', 'Display name for the job')
    }
    steps {
        dsl(seeded_jobs_dsl)
    }
}