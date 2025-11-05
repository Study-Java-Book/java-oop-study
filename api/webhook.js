export default async function handler(req, res) {
  // POST 요청만 처리
  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method not allowed' });
  }

  try {
    const DISCORD_WEBHOOK_URL = process.env.DISCORD_WEBHOOK_URL;
    
    if (!DISCORD_WEBHOOK_URL) {
      return res.status(500).json({ error: 'Discord webhook URL not configured' });
    }

    const payload = req.body;
    let discordMessage = null;

    // 이슈 생성
    if (payload.action === 'opened' && payload.issue) {
      discordMessage = {
        embeds: [{
          title: `🆕 새로운 이슈가 등록되었습니다`,
          description: payload.issue.title,
          color: 0x28a745, // 초록색
          fields: [
            {
              name: '작성자',
              value: payload.issue.user.login,
              inline: true
            },
            {
              name: '저장소',
              value: payload.repository.full_name,
              inline: true
            },
            {
              name: '내용',
              value: payload.issue.body ? 
                (payload.issue.body.length > 200 
                  ? payload.issue.body.substring(0, 200) + '...' 
                  : payload.issue.body)
                : '내용 없음',
              inline: false
            }
          ],
          url: payload.issue.html_url,
          thumbnail: {
            url: payload.issue.user.avatar_url
          },
          timestamp: new Date().toISOString(),
          footer: {
            text: `Issue #${payload.issue.number}`
          }
        }]
      };
    }

    // 이슈 코멘트
    if (payload.action === 'created' && payload.comment && payload.issue) {
      discordMessage = {
        embeds: [{
          title: `💬 새로운 코멘트가 달렸습니다`,
          description: `[${payload.issue.title}](${payload.issue.html_url})`,
          color: 0x0366d6, // 파란색
          fields: [
            {
              name: '작성자',
              value: payload.comment.user.login,
              inline: true
            },
            {
              name: '저장소',
              value: payload.repository.full_name,
              inline: true
            },
            {
              name: '코멘트',
              value: payload.comment.body.length > 300 
                ? payload.comment.body.substring(0, 300) + '...' 
                : payload.comment.body,
              inline: false
            }
          ],
          url: payload.comment.html_url,
          thumbnail: {
            url: payload.comment.user.avatar_url
          },
          timestamp: new Date().toISOString(),
          footer: {
            text: `Issue #${payload.issue.number}`
          }
        }]
      };
    }

    // 이슈 닫힘
    if (payload.action === 'closed' && payload.issue) {
      discordMessage = {
        embeds: [{
          title: `✅ 이슈가 닫혔습니다`,
          description: payload.issue.title,
          color: 0x6f42c1, // 보라색
          fields: [
            {
              name: '닫은 사람',
              value: payload.sender.login,
              inline: true
            },
            {
              name: '저장소',
              value: payload.repository.full_name,
              inline: true
            }
          ],
          url: payload.issue.html_url,
          timestamp: new Date().toISOString(),
          footer: {
            text: `Issue #${payload.issue.number}`
          }
        }]
      };
    }

    // Discord로 메시지 전송
    if (discordMessage) {
      const response = await fetch(DISCORD_WEBHOOK_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(discordMessage)
      });

      if (!response.ok) {
        throw new Error(`Discord API error: ${response.status}`);
      }
    }

    return res.status(200).json({ success: true });
  } catch (error) {
    console.error('Error:', error);
    return res.status(500).json({ error: error.message });
  }
}
