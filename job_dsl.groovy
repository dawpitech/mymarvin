folder('Tools') {
    description('Folder for miscellaneous tools.')

    job('clone-repository') {
        configure { node ->
            node << 'test'
        }
    }
}
