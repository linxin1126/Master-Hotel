import request from '@/utils/request'

const URL = 'op/checkin'

export function checkIn(data) {
  return request({
    url: URL + '/in',
    method: 'post',
    data: data
  })
}

export function checkOut(roomNumber) {
  return request({
    url: URL + '/out',
    method: 'post',
    data: {
      roomNumber: roomNumber
    }
  })
}

export function findCheckInByRoomNumber(roomNumber) {
  return request({
    url: URL + '/find' + roomNumber,
    method: 'get',
  })
}
