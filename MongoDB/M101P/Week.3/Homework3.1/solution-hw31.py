import pymongo

connection = pymongo.MongoClient('mongodb://localhost')
db = connection.school
students = db.students


# function to count the number of students in the collection 
def student_count():
    query = {}

    try:
        count = students.find(query).count()
    except Exception as ex:
        print('Unexpected Error: ', type(ex), ex)

    print('Total number of students :: ', count, '\n')
    return count

# function
def student_detail(student_count):    
    for i in range(0, student_count):
        query = { '_id': i, 'scores.type': 'homework' }
        projection = { 'scores': 1, '_id': 0 }

        try:
            doc = students.find_one(query, projection)
        except Exception as ex:
            print('Unexpected Error: ', type(ex), ex)

        max_score = 0.0
        scores_list = []
        for val in doc['scores']:
            if val['type'] == 'homework':
                if max_score < val['score']:
                    max_score = val['score']
            else:
                scores_list.append(val)

        scores_list.append( { 'score': max_score, 'type': 'homework' } )

        # updating scores_list values in database
        students.update_one( { '_id': i }, { '$set': { 'scores': scores_list } } )
    
    
# function call
count = student_count()
student_detail(count)
